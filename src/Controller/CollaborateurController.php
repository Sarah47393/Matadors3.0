<?php

namespace App\Controller;

use App\Entity\Collaborateur;
use App\Form\CollaborateurType;
use App\Repository\CollaborateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


/**
 * @Route("/collaborateur")
 */
class CollaborateurController extends AbstractController
{
        /**
     * @Route("/AllCollaborateurs", name="AllCollaborateurs")
     */
public function AllCollaborateurs(NormalizerInterface $Normalizer )
{
//Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
$repository =$this->getDoctrine()->getRepository(Collaborateur::class);
$collaborateurs=$repository->FindAll();
//Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
//en tableau d'objet Students
$jsonContent=$Normalizer->normalize($collaborateurs,'json',['groups'=>'post:read']);



return new Response(json_encode($jsonContent));
dump($jsonContent);
die;}
/**
     * @Route("/addCollaborateurC", name="addCollaborateurC")
     */
    public function AddCollaborateurs(Request $request, NormalizerInterface $Normalizer )
    {
    //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
   
    //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
    //en tableau d'objet Students
    $em=$this->getDoctrine()->getManager();
    $collaborateur=new Collaborateur();
  //  $NomCollaborateur = $request->query->get("NomCollaborateur");
  //  $PrenomCollaborateur = $request->query->get("PrenomCollaborateur");
 //   $Role = $request->query->get("Role");
 //   $NumeroTel = $request->query->get("NumeroTel");
    $collaborateur->setNomCollaborateur( $request->get("NomCollaborateur"));
    $collaborateur->setPrenomCollaborateur($request->get("PrenomCollaborateur"));
    $collaborateur->setRole($request->get("Role"));
    $collaborateur->setNumeroTel($request->get("NumeroTel"));
    $em->persist($collaborateur);
    $em->flush();
    $jsonContent=$Normalizer->normalize($collaborateur,'json',['groups'=>'post:read']);
    
    return new Response(json_encode($jsonContent));
    
    }
    /**
     * @Route("/", name="collaborateur_index", methods={"GET"})
     */
    public function index(CollaborateurRepository $collaborateurRepository): Response
    {
        return $this->render('collaborateur/index.html.twig', [
            'collaborateurs' => $collaborateurRepository->findAll(),
        ]);
    }
    /**
     * @Route("/co", name="collaborateur_index1", methods={"GET"})
     */
    public function index1(CollaborateurRepository $collaborateurRepository): Response
    {
        return $this->render('collaborateur/index1.html.twig', [
            'collaborateurs' => $collaborateurRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="collaborateur_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $collaborateur = new Collaborateur();
        $form = $this->createForm(CollaborateurType::class, $collaborateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($collaborateur);
            $entityManager->flush();

            return $this->redirectToRoute('collaborateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('collaborateur/new.html.twig', [
            'collaborateur' => $collaborateur,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="collaborateur_show", methods={"GET"})
     */
    public function show(Collaborateur $collaborateur): Response
    {
        return $this->render('collaborateur/show.html.twig', [
            'collaborateur' => $collaborateur,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="collaborateur_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Collaborateur $collaborateur, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CollaborateurType::class, $collaborateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('collaborateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('collaborateur/edit.html.twig', [
            'collaborateur' => $collaborateur,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="collaborateur_delete", methods={"POST"})
     */
    public function delete(Request $request, Collaborateur $collaborateur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$collaborateur->getId(), $request->request->get('_token'))) {
            $entityManager->remove($collaborateur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('collaborateur_index', [], Response::HTTP_SEE_OTHER);
    }

}
